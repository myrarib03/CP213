package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI for the Order class.
 *
 * @author Myra Ribeiro
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2024-10-15
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    /**
     * Implements an ActionListener for the 'Print' button. Prints the current
     * contents of the Order to a system printer or PDF.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {

	    // your code here
	    try {
		java.awt.print.PrinterJob job = java.awt.print.PrinterJob.getPrinterJob();
		job.setPrintable(order); // Order class should implement Printable

		if (job.printDialog()) {
		    job.print(); // Send the order to the printer
		}
	    } catch (Exception ex) {
		ex.printStackTrace(); // Handle exceptions
	    }
	}

	/**
	 * Implements a FocusListener on a JTextField. Accepts only positive integers,
	 * all other values are reset to 0. Adds a new MenuItem to the Order with the
	 * new quantity, updates an existing MenuItem in the Order with the new
	 * quantity, or removes the MenuItem from the Order if the resulting quantity is
	 * 0.
	 */
	private class QuantityListener implements FocusListener {
	    private MenuItem item = null;

	    QuantityListener(final MenuItem item) {
		this.item = item;
	    }

	    // your code here
	    @Override
	    public void focusGained(final FocusEvent e) {
	    }

	    @Override
	    public void focusLost(final FocusEvent e) {
		JTextField quantityText = (JTextField) e.getSource();
		String quantityStr = quantityText.getText();
		int quanity = 0;
		try {
		    quanity = Integer.parseInt(quantityStr);
		    if (quanity < 0) {
			quanity = 0;
		    }
		} catch (NumberFormatException error) {
		    quanity = 0;
		}

		order.update(item, quanity);

		subtotalLabel.setText(String.format("%.2f", order.getSubTotal().floatValue()));
		taxLabel.setText(String.format("%.2f", order.getTaxes().floatValue()));
		totalLabel.setText(String.format("%.2f", order.getTotal().floatValue()));

		String qToString = String.valueOf(quanity);
		quantityText.setText(qToString);
	    }

	}

	// Attributes
	private Menu menu = null;
	private final Order order = new Order();
	private final DecimalFormat priceFormat = new DecimalFormat("$##0.00");
	private final JButton printButton = new JButton("Print");
	private final JLabel subtotalLabel = new JLabel("0");
	private final JLabel taxLabel = new JLabel("0");
	private final JLabel totalLabel = new JLabel("0");

	private JLabel nameLabels[] = null;
	private JLabel priceLabels[] = null;
	// TextFields for menu item quantities.
	private JTextField quantityFields[] = null;

    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     */
    public OrderPanel(final Menu menu) {
	this.menu = menu;
	this.nameLabels = new JLabel[this.menu.size()];
	this.priceLabels = new JLabel[this.menu.size()];
	this.quantityFields = new JTextField[this.menu.size()];
	this.layoutView();
	this.registerListeners();
    }

	/**
	 * Uses the GridLayout to place the labels and buttons.
	 */
	private void layoutView() {

	    int rows = this.menu.size() + 4;
	    this.setLayout(new GridLayout(rows, 4));
	    this.add(new JLabel("Item"));
	    this.add(new JLabel("Price"));
	    this.add(new JLabel("Quantity"));
	    this.add(new JLabel(""));
	    for (int i = 0; i < this.menu.size(); i++) {
		MenuItem item = this.menu.getItem(i);

		this.nameLabels[i] = new JLabel(item.getName());
		this.priceLabels[i] = new JLabel(priceFormat.format(item.getPrice()));
		this.quantityFields[i] = new JTextField("0");

		this.add(this.nameLabels[i]);
		this.add(this.priceLabels[i]);
		this.add(this.quantityFields[i]);
		this.add(new JLabel(""));
	    }
	    this.add(new JLabel("Subtotal:"));
	    this.add(this.subtotalLabel);
	    this.add(new JLabel(""));
	    this.add(new JLabel(""));

	    this.add(new JLabel("Taxes:"));
	    this.add(this.taxLabel);
	    this.add(new JLabel(""));
	    this.add(new JLabel(""));

	    this.add(new JLabel("Total:"));
	    this.add(this.totalLabel);
	    this.add(new JLabel("")); // Empty cell
	    this.add(new JLabel(""));
	    this.add(new JLabel(""));
	    this.add(this.printButton);
	    this.add(new JLabel(""));
	    this.add(new JLabel(""));
	}

	/**
	 * Register the widget listeners with the widgets.
	 */
	private void registerListeners() {
	    this.printButton.addActionListener(new PrintListener());
	    // your code here
	    for (int i = 0; i < menu.size(); i++) {
		MenuItem item = menu.getItem(i);
		quantityFields[i].addFocusListener(new QuantityListener(item));
	    }
	}
    }
}