package src.main;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class Appointment
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public class Appointment {
    private String date;        // format: yyyy-MM-dd
    private String startTime;   // format: hh:mm a
    private String endTime;     // format: hh:mm a
    private String patientName;
    private String doctorName;
    private String status;

    static Scanner input = new Scanner(System.in);

    private SimpleDateFormat timeFormat = new SimpleDateFormat(
                "hh:mm a");
    private SimpleDateFormat dayFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
    
    public Appointment() {
        setDate("");
        setStartTime("");
        setEndTime("");
        setAppointment("", "", "Confirmed");
    }
    
    public void setAppointment(String patientName, String doctorName, String status) {   // same as edit appointment
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.status = status;
    }

    public void setAppointmentDate() {
        System.out.print("Enter a date in the format yyyy mm dd: ");
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, input.nextInt());
        date.set(Calendar.MONTH, input.nextInt() - 1);
        date.set(Calendar.DAY_OF_MONTH, input.nextInt());
        setDate(dayFormat.format(date.getTime()));
        
        System.out.print("Enter the starting time in the format hh mm: ");
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, input.nextInt());
        startTime.set(Calendar.MINUTE, input.nextInt());
        setStartTime(timeFormat.format(startTime.getTime()));

        System.out.print("Enter the ending time in the format hh mm: ");
        Calendar endTime = Calendar.getInstance();
        endTime.set(Calendar.HOUR_OF_DAY, input.nextInt());
        endTime.set(Calendar.MINUTE, input.nextInt());
        setEndTime(timeFormat.format(endTime.getTime()));

        System.out.println();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return this.date;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String getDoctorName() {
        return this.doctorName;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return String.format("%s: %s%n%s: %s%n%s: %s%n%s: %s%n%s: %s%n%s: %s%n",
        "Date", getDate(),
        "Start Time", getStartTime(),
        "End Time", getEndTime(),
        "Doctor Name", getDoctorName(),
        "Patient Name", getPatientName(),
        "Status", getStatus()
        );
    }

}
