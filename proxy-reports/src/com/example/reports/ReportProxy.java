package com.example.reports;

/**
 * TODO (student):
 * Implement Proxy responsibilities here:
 * - access check
 * - lazy loading
 * - caching of RealReport within the same proxy
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;

    private RealReport realReport;
    private final AccessControl accessControl = new AccessControl();

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {

        System.out.println("[proxy] request to open report " + reportId + " by " + user.getName());

        if (!accessControl.canAccess(user, classification)) {
            System.out.println("[proxy] ACCESS DENIED for user " + user.getName());
            return;
        }
        if (realReport == null) {
            System.out.println("[proxy] lazy loading real report...");
            realReport = new RealReport(reportId, title, classification);
        }
        realReport.display(user);
    }
}
