import examinationTests.BookTest;
import examinationTests.LibraryTest;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.engine.discovery.DiscoverySelectors;

import javax.swing.*;
import java.awt.*;


/**
 * Class to run all tests and display results as a pie chart
 */
public class TestResultsVisualizer {

    public static void main(String[] args) {
        // Create a launcher and discovery request
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectClass(BookTest.class))
                .selectors(DiscoverySelectors.selectClass(LibraryTest.class))
                .build();
        Launcher launcher = LauncherFactory.create();

        // Create and register a listener for test results
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);

        // Execute tests
        launcher.execute(request);

        // Get test summary
        TestExecutionSummary summary = listener.getSummary();
        long totalTests = summary.getTestsFoundCount();
        long passedTests = totalTests - summary.getTestsFailedCount();
        double passRatePercent = (double) passedTests / totalTests * 100;

        // Display the results in console
        System.out.println("===== PROJECT TEST RESULTS =====");
        System.out.println("Total tests: " + totalTests);
        System.out.println("Passed tests: " + passedTests);
        System.out.println("Failed tests: " + summary.getTestsFailedCount());
        System.out.println("Pass rate: " + String.format("%.2f%%", passRatePercent));
        System.out.println("========================");

        // Display results in a pie chart
        SwingUtilities.invokeLater(() -> createAndShowPieChart(passedTests, summary.getTestsFailedCount()));
    }

    private static void createAndShowPieChart(long passed, long failed) {
        JFrame frame = new JFrame("Test Results");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        PieChartPanel chart = new PieChartPanel(passed, failed);
        frame.add(chart);

        frame.setVisible(true);
    }

    /**
     * Simple Swing panel to render a pie chart of test results
     */
    static class PieChartPanel extends JPanel {
        private final long passedTests;
        private final long failedTests;

        public PieChartPanel(long passed, long failed) {
            this.passedTests = passed;
            this.failedTests = failed;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Anti-aliasing for smoother rendering
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Calculate total and angles
            long total = passedTests + failedTests;
            int passedAngle = (int) Math.round(360.0 * passedTests / total);

            // Set up dimensions
            int padding = 50;
            int diameter = Math.min(getWidth(), getHeight()) - 2 * padding;
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;

            // Draw pie chart
            g2d.setColor(Color.GREEN);
            g2d.fillArc(x, y, diameter, diameter, 0, passedAngle);
            g2d.setColor(Color.RED);
            g2d.fillArc(x, y, diameter, diameter, passedAngle, 360 - passedAngle);

            // Draw border
            g2d.setColor(Color.BLACK);
            g2d.drawOval(x, y, diameter, diameter);

            // Draw legend
            int legendX = x + diameter + 20;
            int legendY = y + diameter / 2;

            g2d.setColor(Color.GREEN);
            g2d.fillRect(legendX, legendY - 30, 20, 20);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Passed: " + passedTests + " (" +
                            String.format("%.1f", 100.0 * passedTests / total) + "%)",
                    legendX + 30, legendY - 15);

            g2d.setColor(Color.RED);
            g2d.fillRect(legendX, legendY, 20, 20);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Failed: " + failedTests + " (" +
                            String.format("%.1f", 100.0 * failedTests / total) + "%)",
                    legendX + 30, legendY + 15);

            // Draw title
            g2d.setFont(new Font("Arial", Font.BOLD, 18));
            g2d.drawString("Test Results (Nathan) : " + String.format("%.1f", 100.0 * passedTests / total) + "% Pass Rate",
                    getWidth() / 2 - 150, padding / 2);
        }
    }
}