package veryfi.enums;

public enum Endpoint {
    documents("/partner/documents/"),
    anyDocuments("/partner/any-documents/"),
    bankStatements("/partner/bank-statements/"),
    businessCards("/partner/business-cards/"),
    checks("/partner/checks/"),
    w2s("/partner/w2s/"),
    w9s("/partner/w9s/"),
    w8BenE("/partner/w-8ben-e/"),
    contracts("/partner/contracts/");

    public final String path;

    private Endpoint(String path) {
        this.path = path;
    }
}