package veryfi.models;

import org.json.JSONObject;

/**
 * Model class for adding line items to documents.
 * <p>
 * This class extends {@link SharedLineItem} and provides the necessary fields
 * for creating new line items in Veryfi documents. The required fields are
 * order, description, and total, while all other fields are optional.
 * </p>
 * 
 * @see SharedLineItem
 * @see UpdateLineItem
 * @see NotValidModelException
 */
public class AddLineItem extends SharedLineItem {
    /**
     * The order/position of the line item in the document.
     * This field is required and cannot be null.
     */
    public Integer order;
    
    /**
     * The description or name of the line item.
     * This field is required and cannot be null.
     */
    public String description;
    
    /**
     * The total amount for this line item.
     * This field is required and cannot be null.
     */
    public Float total;

    /**
     * Creates a new AddLineItem with the required fields.
     * 
     * @param order The order/position of the line item
     * @param description The description or name of the line item
     * @param total The total amount for this line item
     */
    public AddLineItem(Integer order, String description, Float total) {
        this.order = order;
        this.description = description;
        this.total = total;
    }

    /**
     * Convert current object to a Json
     * @return Json object
     * @throws NotValidModelException throws when the model is not valid.
     */
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
