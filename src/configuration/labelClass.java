package configuration;
import javax.swing.JLabel;


public class labelClass {
	String name,defaultPic,altPic,disabledPic;
	JLabel jl;
	Boolean enabled;
	public labelClass(JLabel jl,String name,String defaultPic,String altPic,String disabledPic,Boolean enabled) {
		this.jl=jl;
		this.name=name;
		this.defaultPic=defaultPic;
		this.altPic=altPic;
		this.enabled=enabled;
		this.disabledPic=disabledPic;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDefaultPic() {
		return defaultPic;
	}
	public void setDefaultPic(String defaultPic) {
		this.defaultPic = defaultPic;
	}
	public String getAltPic() {
		return altPic;
	}
	public void setAltPic(String altPic) {
		this.altPic = altPic;
	}
	public JLabel getJl() {
		return jl;
	}
	public void setJl(JLabel jl) {
		this.jl = jl;
	}
	public void setDisabledPic(String disabledPic) {
		this.disabledPic = disabledPic;
	}
	public String getDisabledPic() {
		return disabledPic;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	
	
}
