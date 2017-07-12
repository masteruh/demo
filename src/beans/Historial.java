package beans;


import java.util.Date;
import java.util.TimeZone;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Historial{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String fecha;
					
	public Historial() {}
	public Historial(String f) {
		this.fecha = f;
	}
	
	public String getFecha() {
	  return fecha;
	}
	public void setFecha(String fecha) {
	  this.fecha = fecha;
	}
		public Key getKey() {
		return key;
	}
	
	
}
