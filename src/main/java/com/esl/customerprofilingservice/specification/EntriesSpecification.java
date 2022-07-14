package com.esl.billermanagerservice.specification;

import com.esl.billermanagerservice.model.Entries;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntriesSpecification {
    public static Specification<Entries> withCustomerIdentifier(String customerIdentifier) {
        if (customerIdentifier == null)
            return null;

        return (root, query, cb) -> cb.equal(root.get("customerIdentifier"), customerIdentifier);
    }

    public static Specification<Entries> withOwner(String owner) {
        if (owner == null)
            return null;

        return (root, query, cb) -> cb.equal(root.get("owner"), owner);
    }


}
