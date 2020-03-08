package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="GreenHouseStat")
public class GreenHouseStat {
	@Column(name="GreenHouseStat_Gno")
	private long Gno;
	@Column(name="GreenHouseStat_Temper")
	private float temper;
	@Column(name="GreenHouseStat_Hum")
	private float hum;
	@Column(name="GreenHouseStat_Smoke")
	private String smoke;
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="GreenHouseStat_Date")
	private Date date;
	
	public GreenHouseStat(){}
	
	public GreenHouseStat(long Gno, float temper, float hum, String smoke, Date date) {
		super();
		this.Gno = Gno;
		this.temper = temper;
		this.hum = hum;
		this.smoke = smoke;
		this.date = date;
	}

	public long getGno() {
		return Gno;
	}

	public void setGno(long Gno) {
		this.Gno = Gno;
	}

	public float getTemper() {
		return temper;
	}

	public void setTemper(float temper) {
		this.temper = temper;
	}

	public float getHum() {
		return hum;
	}

	public void setHum(float hum) {
		this.hum = hum;
	}

	public String getSmoke() {
		return smoke;
	}

	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
