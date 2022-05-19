package se.kth.iv1350.pos.view;
import se.kth.iv1350.pos.util.Amount;
import se.kth.iv1350.pos.model.PaidAmountObserver;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TotalRevenueFileOutput implements PaidAmountObserver {
	private Amount totAmountPaid;
	private Amount startAmount;
	private PrintWriter revenueLogFile;
	//private static final String LOG_FILE_NAME = "revenueLog.txt";
	
	public TotalRevenueFileOutput() throws IOException {
		//this.totAmountPaid = startAmount;
		this.totAmountPaid = startAmount;
		
		try {
			revenueLogFile = new PrintWriter(new FileWriter("revenueLog.txt"), true);
		} catch (IOException exception) {
			System.out.println("Failed to log total revenue due to an error");
			exception.printStackTrace();
		}
	}

	@Override
	public void newPayment(Amount amountPaid) {
		totAmountPaid = totAmountPaid.add(amountPaid);
		revenueLogFile.println(currentLocalTime() + "\nThe total amount paid since start of program: " + totAmountPaid);
	}
	
	private String currentLocalTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		return now.format(formatter) + ", ";
	}

}
