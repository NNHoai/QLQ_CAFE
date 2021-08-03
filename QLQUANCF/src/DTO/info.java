package DTO;

public class info {
		private Integer stt;
		private String ten;
		private String lienhe;
		private String diachi;
		public info() {
		}
	        
	    public void copy(info i){
	    	this.setStt(i.getStt());
	        this.setTen(i.getTen());
	        this.setLienhe(i.getLienhe());
	        this.setDiachi(i.getDiachi());
	    }
		
		public info(Integer stt, String ten, String lh, String dc) {
			this.stt = stt;
			this.ten = ten;
			this.lienhe = lh;
			this.diachi = dc;
		}

		public String getLienhe() {
			return lienhe;
		}

		public void setLienhe(String lienhe) {
			this.lienhe = lienhe;
		}

		public String getTen() {
			return ten;
		}

		public void setTen(String ten) {
			this.ten = ten;
		}

		public String getDiachi() {
			return diachi;
		}

		public void setDiachi(String diachi) {
			this.diachi = diachi;
		}

		public Integer getStt() {
			return stt;
		}

		public void setStt(Integer stt) {
			this.stt = stt;
		}
}
