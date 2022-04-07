package veryfi.models;

import org.json.JSONObject;

public class AddLineItem extends SharedLineItem {
    public Integer order;
    public String description;
    public Float total;

    public AddLineItem(Integer order, String description, Float total) {
        this.order = order;
        this.description = description;
        this.total = total;
    }

    public final JSONObject toJsonObject() throws NotValidModelException {
        JSONObject jsonObject = new JSONObject();
        if (order == null || description == null || total == null) throw new NotValidModelException("order, description and total can't be null");
        jsonObject.put("order", order);
        jsonObject.put("description", description);
        jsonObject.put("total", total);
        if (sku != null) jsonObject.put("sku", sku);
        if (category != null) jsonObject.put("category", category);
        if (tax != null) jsonObject.put("tax", tax);
        if (price != null) jsonObject.put("price", price);
        if (unitOfMeasure != null) jsonObject.put("unit_of_measure", unitOfMeasure);
        if (quantity != null) jsonObject.put("quantity", quantity);
        if (upc != null) jsonObject.put("upc", upc);
        if (taxRate != null) jsonObject.put("tax_rate", taxRate);
        if (discountRate != null) jsonObject.put("discount_rate", discountRate);
        if (startDate != null) jsonObject.put("start_date", startDate);
        if (endDate != null) jsonObject.put("end_date", endDate);
        if (hsn != null) jsonObject.put("hsn", hsn);
        if (section != null) jsonObject.put("section", section);
        if (weight != null) jsonObject.put("weight", weight);
        return jsonObject;
    }
}
