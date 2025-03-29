package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.purchase.Purchase;
import com.drivelab.autocenter.rest.purchase.PurchaseRemoveItemResponseBody.Item;
import com.drivelab.autocenter.rest.purchase.PurchaseRemoveItemResponseBody.Supplier;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PurchaseRemoveItemResponseMapping {
    public PurchaseRemoveItemResponseBody responseBody(@NonNull Purchase purchase) {
        return new PurchaseRemoveItemResponseBody(purchase.publicId().toString(),
                purchase.total().decimal(),
                supplier(purchase),
                items(purchase));
    }

    private List<Item> items(@NonNull Purchase purchase) {
        return purchase.items()
                .stream()
                .map(item -> new Item(item.quantity(), item.product().publicId().toString(),
                        item.unitCost().decimal()))
                .collect(Collectors.toList());
    }

    private Supplier supplier(@NonNull Purchase purchase) {
        return new Supplier(purchase.supplier().publicId().toString(), purchase.supplier().name().toString());
    }
}
