package Factory;

public interface Product {
    // Product interface
        void doSomething();
    }

    // Concrete products
    class ConcreteProductA implements Product {
        @Override
        public void doSomething() {
            System.out.println("Product A is doing something.");
        }
    }

    class ConcreteProductB implements Product {
        @Override
        public void doSomething() {
            System.out.println("Product B is doing something.");
        }
    }

    // Factory interface
    interface ProductFactory {
        Product createProduct();
    }

    // Concrete factory implementations
    class ConcreteProductFactoryA implements ProductFactory {
        @Override
        public Product createProduct() {
            return new ConcreteProductA();
        }
    }

    class ConcreteProductFactoryB implements ProductFactory {
        @Override
        public Product createProduct() {
            return new ConcreteProductB();
        }
    }

