#!/bin/sh

# this is a script to check that the new deployment completed successfully and if not, a rollback is done

RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m' # No Color

# wait for 2 minutes to check the deployment status
sleep 180

# get kubeconfig file
doctl -t ${DOCTL_TOKEN} k8s cluster kubeconfig show ${K8S_CLUSTER_NAME} > kubeconfig.yml

# check the status of the last rollout 
kubectl --insecure-skip-tls-verify --kubeconfig=kubeconfig.yml rollout status deployment billermanager

if [[ $? == 1 ]]; then
  echo -e "${RED} ERROR: DEPLOYMENT ROLLOUT FAILED :( ${NC}" 
  kubectl --insecure-skip-tls-verify --kubeconfig=kubeconfig.yml rollout undo deployment billermanager
else
  echo -e "${GREEN} INFO: DEPLOYMENT ROLLOUT SUCCEEDED :) ${NC}" 
fi