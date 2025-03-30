package com.drivelab.autocenter.rest.purchase;

import com.drivelab.autocenter.domain.product.ProductPublicId;
import com.drivelab.autocenter.domain.purchase.PurchaseGoodsReceivingCommand;
import com.drivelab.autocenter.domain.purchase.PurchaseGoodsReceivingCommand.Item;
import com.drivelab.autocenter.domain.purchase.PurchasePublicId;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PurchaseGoodsReceivingRequestBodyMapping {

    public PurchaseGoodsReceivingCommand command(@NonNull PurchasePublicId id, @NonNull PurchaseGoodsReceivingRequestBody requestBody) {
        return new PurchaseGoodsReceivingCommand(id, items(requestBody));
    }

    private List<Item> items(PurchaseGoodsReceivingRequestBody requestBody) {
        return requestBody.getItems()
                .stream()
                .map(i -> new Item(new ProductPublicId(i.getProductId()), i.getQuantity()))
                .collect(Collectors.toList());
    }
}
