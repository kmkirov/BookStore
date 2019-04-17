package model;

import java.util.HashMap;
import java.util.Map;

public class SalesOrder {


    public enum SalesOrderTupe {
        Restocked(0),
        Purched(1),
        Requested(2),
        Completed(3);

        private int value;
        private static Map map = new HashMap<>();

        private SalesOrderTupe(int value) {
            this.value = value;
        }

        static {
            for (SalesOrderTupe pageType : SalesOrderTupe.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static SalesOrderTupe valueOf(int pageType) {
            return (SalesOrderTupe) map.get(pageType);
        }

        public int getValue() {
            return value;
        }
    }

    private Integer ItemId; // fk
    private Integer SalesId; // pk
    private Integer CustId; //fk
    private Integer SalesType; // desc
    private Integer Qty; //des


    public void print()
    {
        System.out.printf("SalesID: %d, ItemId: %d, CustId: %d, SalesType: %s, Qty: %d \n",SalesId,ItemId,CustId,SalesOrderTupe.valueOf(SalesType),Qty);
    }
    public SalesOrder(Integer itemId, Integer salesId, Integer custId, Integer salesType, Integer qty) {
        ItemId = itemId;
        SalesId = salesId;
        CustId = custId;
        SalesType = salesType;
        Qty = qty;
    }

    public Integer getItemId() {
        return ItemId;
    }

    public void setItemId(Integer itemId) {
        ItemId = itemId;
    }

    public Integer getSalesId() {
        return SalesId;
    }

    public void setSalesId(Integer salesId) {
        SalesId = salesId;
    }

    public Integer getCustId() {
        return CustId;
    }

    public void setCustId(Integer custId) {
        CustId = custId;
    }

    public Integer getSalesType() {
        return SalesType;
    }

    public void setSalesType(Integer salesType) {
        SalesType = salesType;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
    }
}
