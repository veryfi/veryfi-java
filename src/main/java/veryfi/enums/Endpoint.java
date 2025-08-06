package veryfi.enums;

/**
 * Enumeration of Veryfi API endpoints.
 * <p>
 * This enum defines all the available API endpoints for different document types
 * and operations in the Veryfi API. Each endpoint represents a specific resource
 * or operation that can be performed.
 * </p>
 */
public enum Endpoint {
    /**
     * Endpoint for general documents (receipts, invoices, etc.).
     * Path: /partner/documents/
     */
    documents("/partner/documents/"),
    
    /**
     * Endpoint for any document type processing.
     * Path: /partner/any-documents/
     */
    anyDocuments("/partner/any-documents/"),
    
    /**
     * Endpoint for bank statement processing.
     * Path: /partner/bank-statements/
     */
    bankStatements("/partner/bank-statements/"),
    
    /**
     * Endpoint for business card processing.
     * Path: /partner/business-cards/
     */
    businessCards("/partner/business-cards/"),
    
    /**
     * Endpoint for check processing.
     * Path: /partner/checks/
     */
    checks("/partner/checks/"),
    
    /**
     * Endpoint for W2 form processing.
     * Path: /partner/w2s/
     */
    w2s("/partner/w2s/"),
    
    /**
     * Endpoint for W9 form processing.
     * Path: /partner/w9s/
     */
    w9s("/partner/w9s/"),
    
    /**
     * Endpoint for W-8BEN-E form processing.
     * Path: /partner/w-8ben-e/
     */
    w8BenE("/partner/w-8ben-e/"),
    
    /**
     * Endpoint for contract processing.
     * Path: /partner/contracts/
     */
    contracts("/partner/contracts/"),
    
    /**
     * Endpoint for document classification.
     * Path: /partner/classify/
     */
    classify("/partner/classify/"),
    
    /**
     * Endpoint for document splitting operations.
     * Path: /partner/documents-set/
     */
    split("/partner/documents-set/");

    /**
     * The API path for this endpoint.
     */
    public final String path;

    /**
     * Creates a new Endpoint with the specified path.
     * 
     * @param path The API path for this endpoint
     */
    private Endpoint(String path) {
        this.path = path;
    }
}