public class Builderr {
    class Product {
        private String property1;
        private int property2;
        // Other properties

        private Product() {
            // Private constructor to prevent instantiation without using the builder
        }

        public class Builder {
            private String property1;
            private int property2;
            // Other properties

            public Builder() {
                // Initialize default values if needed
            }

            public Builder withProperty1(String value) {
                this.property1 = value;
                return this;
            }

            public Builder withProperty2(int value) {
                this.property2 = value;
                return this;
            }

            // Other setter methods for additional properties

            public Product build() {
                Product product = new Product();
                product.property1 = this.property1;
                product.property2 = this.property2;
                // Set other properties
                return product;
            }
        }

        // Getters for properties
    }

}
