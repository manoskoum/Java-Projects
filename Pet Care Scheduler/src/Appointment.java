import java.io.Serial;
import java.io.Serializable;

import java.time.LocalDateTime;


public class Appointment implements Serializable {

    private String type;

    private LocalDateTime dateTime;

    private String notes;

   public Appointment(String type, LocalDateTime dateTime, String notes) {
        this.type = type;
        this.dateTime = dateTime;
        this.notes = notes;
    }

    public Appointment(String type, LocalDateTime dateTime) {
        this.type = type;
        this.dateTime = dateTime;

    }

        public String getType(){
       return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDateTime(){
       return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getNotes(){
       return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "type='" + type + '\'' +
                ", dateTime=" + dateTime +
                ", notes='" + notes + '\'' +
                '}';
    }
}

