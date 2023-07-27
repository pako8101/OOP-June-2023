
    // Assume we have a prototype interface
    interface Prototype {
        Prototype clone();
    }

    // Concrete prototype implementation
    class ConcretePrototype implements Prototype {
        private int field;

        public ConcretePrototype(int field) {
            this.field = field;
        }

        @Override
        public Prototype clone() {
            return new ConcretePrototype(this.field);
        }

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }
    }


