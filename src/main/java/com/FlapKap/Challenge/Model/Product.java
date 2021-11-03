package com.FlapKap.Challenge.Model;

import javax.persistence.*;

@Entity
@Table(name="Products")
public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @Column(name="amountAvailable")
        private float amountAvailable;

        @Column(name="cost")
        private float cost;

        @Column(name="productName")
        private String productName;

        @Column(name="sellerId")
        private long sellerId;

        public Product () {

        }

        public Product ( float amountAvailable, float cost, String productName, long sellerId ) {

                this.amountAvailable = amountAvailable;
                this.cost = cost;
                this.productName = productName;
                this.sellerId = sellerId;

        }

        public float getAmountAvailable() {
                return amountAvailable;
        }

        public void setAmountAvailable(float amountAvailable) {
                this.amountAvailable = amountAvailable;
        }

        public float getCost() {
                return cost;
        }

        public void setCost(float cost) {
                this.cost = cost;
        }

        public String getProductName() {
                return productName;
        }

        public void setProductName(String productName) {
                this.productName = productName;
        }

        public long getSellerId() {
                return sellerId;
        }

        public void setSellerId(long sellerId) {
                this.sellerId = sellerId;
        }

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

}
