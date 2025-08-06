package veryfi.models;

/**
 * Abstract base class for line item models.
 * <p>
 * This class contains all the common fields that are shared between
 * {@link AddLineItem} and {@link UpdateLineItem}. These fields represent
 * optional properties that can be set on line items in Veryfi documents.
 * </p>
 * 
 * @see AddLineItem
 * @see UpdateLineItem
 */
public abstract class SharedLineItem {
    /**
     * Stock Keeping Unit (SKU) for the line item.
     * Optional field that can be null.
     */
    public String sku;
    
    /**
     * Category classification for the line item.
     * Optional field that can be null.
     */
    public String category;
    
    /**
     * Tax amount for the line item.
     * Optional field that can be null.
     */
    public Float tax;
    
    /**
     * Unit price for the line item.
     * Optional field that can be null.
     */
    public Float price;
    
    /**
     * Unit of measure for the line item (e.g., "each", "kg", "lb").
     * Optional field that can be null.
     */
    public String unitOfMeasure;
    
    /**
     * Quantity of the line item.
     * Optional field that can be null.
     */
    public Float quantity;
    
    /**
     * Universal Product Code (UPC) for the line item.
     * Optional field that can be null.
     */
    public String upc;
    
    /**
     * Tax rate percentage for the line item.
     * Optional field that can be null.
     */
    public Float taxRate;
    
    /**
     * Discount rate percentage for the line item.
     * Optional field that can be null.
     */
    public Float discountRate;
    
    /**
     * Start date for the line item (format: YYYY-MM-DD).
     * Optional field that can be null.
     */
    public String startDate;
    
    /**
     * End date for the line item (format: YYYY-MM-DD).
     * Optional field that can be null.
     */
    public String endDate;
    
    /**
     * Harmonized System Nomenclature (HSN) code for the line item.
     * Optional field that can be null.
     */
    public String hsn;
    
    /**
     * Section or department for the line item.
     * Optional field that can be null.
     */
    public String section;
    
    /**
     * Weight of the line item.
     * Optional field that can be null.
     */
    public String weight;
}
