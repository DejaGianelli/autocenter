package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.product.Product;
import com.drivelab.autocenter.domain.product.ProductNotFoundException;
import com.drivelab.autocenter.domain.product.ProductRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PurchaseRemoveItemUseCase {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;

    public PurchaseRemoveItemUseCase(PurchaseRepository purchaseRepository,
                                     ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
    }

    public Purchase udpatedPurchase(@NonNull PurchaseRemoveItemCommand command) {
        Optional<Purchase> purchaseOptional = purchaseRepository.findByPublicIdLocked(command.purchaseId());
        if (purchaseOptional.isEmpty()) {
            throw new PurchaseNotFoundException(command.purchaseId());
        }
        Purchase purchase = purchaseOptional.get();

        Optional<Product> productOptional = productRepository.findByPublicId(command.productId());
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(command.productId());
        }
        Product product = productOptional.get();

        purchase.removeItem(product);

        return purchaseRepository.save(purchase);
    }
}
