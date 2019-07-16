import javax.swing.JLabel;

public class labelClass {
	String name,defaultPic,altPic;
	JLabel jl;
	Object obj;
	guiSuper superObj;
	public labelClass(JLabel jl,String name,String defaultPic,String altPic) {
		this.jl=jl;
		this.name=name;
		this.defaultPic=defaultPic;
		this.altPic=altPic;
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
	public void setObject(guiSuper newObj) {
		//superObj = new (newObj().getClass());
		//superObj = new (newObj.class);
	
	}
	public Object getObject() {
		
		return obj;
	}
}
