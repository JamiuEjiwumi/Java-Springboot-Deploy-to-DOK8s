#For Jenkins
clean:
	gradle clean
build: clean
	gradle build -x test

test: clean
	gradle test

analyze:
	gradle sonarqube

docker-image:
	docker build -t jamiu-limited/images:billermanager-service .
	docker push jamiu-limited/images:billermanager-service

#For Bitbucket Pipeline kubernetes deployment 
gradle-test:
	gradle clean 
	gradle test 
	gradle sonarqube -Dsonar.login=${SONAR_TOKEN} -Dsonar.host.url=${SONAR_URL} -Dsonar.qualitygate.wait=true -Dsonar.verbose=true --stacktrace --debug

doctl-login:
	doctl auth init -t ${DOCTL_TOKEN}

docker-registry-login: 
	doctl registry login

pr-docker-build:
	docker build -t registry.digitalocean.com/jamiu/billermanager:${BITBUCKET_BRANCH}_${BITBUCKET_BUILD_NUMBER} .

pr-docker-push: doctl-login docker-registry-login pr-docker-build
	docker push registry.digitalocean.com/jamiu/billermanager:${BITBUCKET_BRANCH}_${BITBUCKET_BUILD_NUMBER}

docker-build:
	docker build -t registry.digitalocean.com/jamiu/billermanager:${BITBUCKET_TAG}_${BITBUCKET_BUILD_NUMBER} .

docker-push: doctl-login docker-registry-login docker-build
	docker push registry.digitalocean.com/jamiu/billermanager:${BITBUCKET_TAG}_${BITBUCKET_BUILD_NUMBER}

get-kubeconfig:
	doctl -t ${DOCTL_TOKEN} k8s cluster kubeconfig show ${K8S_CLUSTER_NAME} > kubeconfig.yml

pr-dev-apply-deploy: get-kubeconfig
	kubectl --insecure-skip-tls-verify --kubeconfig=kubeconfig.yml set image deployment/billermanager billermanager=registry.digitalocean.com/jamiu/billermanager:${BITBUCKET_BRANCH}_${BITBUCKET_BUILD_NUMBER} --record

pr-dev-staging-apply-deploy: get-kubeconfig
	kubectl --insecure-skip-tls-verify --kubeconfig=kubeconfig.yml set image deployment/billermanager billermanager=registry.digitalocean.com/jamiu/billermanager:${BITBUCKET_BRANCH}_${BITBUCKET_BUILD_NUMBER} --record

staging-prod-apply-deploy: get-kubeconfig
	kubectl --insecure-skip-tls-verify --kubeconfig=kubeconfig.yml set image deployment/billermanager billermanager=registry.digitalocean.com/jamiu/billermanager:${BITBUCKET_BRANCH}_${BITBUCKET_BUILD_NUMBER} --record

prod-apply-deploy: get-kubeconfig
	kubectl --insecure-skip-tls-verify --kubeconfig=kubeconfig.yml set image deployment/billermanager billermanager=registry.digitalocean.com/jamiu/billermanager:${BITBUCKET_TAG}_${BITBUCKET_BUILD_NUMBER} --record

prod-rollback: get-kubeconfig
	sh statuscheck.sh

check-deployment-status: get-kubeconfig
	kubectl --insecure-skip-tls-verify --kubeconfig=kubeconfig.yml get pods | grep billermanager