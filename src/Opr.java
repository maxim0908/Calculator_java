public enum Opr {
    PLUS {
        public int action(String x, String y) {
            int op = Integer.parseInt(x)+Integer.parseInt(y);
            return op;
        }},
    UMN{
        public int action(String x, String y) {
            int op = Integer.parseInt(x)*Integer.parseInt(y);
            return op;
        }},
    DEL{
        public int action(String x, String y) {
            int op = Integer.parseInt(x)/Integer.parseInt(y);
            return op;
        }},
    MINUS{
        public int action(String x, String y) {
            int op = Integer.parseInt(x)-Integer.parseInt(y);
            return op;
        }};

    public abstract int action(String x, String y);
}
