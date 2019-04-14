package model;

public class SalesOrder {
    public enum SalesOrderTupe {
        Restocked,
        Purched,
        Requested,
        Completed};


    private String SalesId; // pk
    private String CustId; //fk
    private SalesOrderTupe SalesType; // desc
    private Integer Qty; //des

        public String getSalesId() {
            return SalesId;
        }

        public void setSalesId(String salesId) {
            SalesId = salesId;
        }

        public String getCustId() {
            return CustId;
        }

        SalesOrder(String salesId, String custId, SalesOrderTupe salesType, Integer qty, String itemId) {
            SalesId = salesId;
            CustId = custId;
            SalesType = salesType;
            Qty = qty;
            ItemId = itemId;
        }

        public void setCustId(String custId) {
            CustId = custId;
        }

        public SalesOrderTupe getSalesType() {
            return SalesType;
        }

        public void setSalesType(SalesOrderTupe salesType) {
            SalesType = salesType;
        }

        public Integer getQty() {
            return Qty;
        }

        public void setQty(Integer qty) {
            Qty = qty;
        }

        public String getItemId() {
            return ItemId;
        }

        public void setItemId(String itemId) {
            ItemId = itemId;
        }

        private String ItemId; // fk
    // opendata and close date here
}
