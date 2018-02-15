import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;

public class RadioEvent implements ItemListener{

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		JRadioButton source= (JRadioButton) e.getItem();
		if (source.isSelected()) {
			String rlabel = source.getText();
			if (rlabel=="Savings") {
				P.t = "S";
			}
			if (rlabel=="Current") {
				P.t = "C";
			}
			if (rlabel=="Male") {
				P.g = "M";
			}
			if (rlabel=="Female") {
				P.g = "F";
			}
			
		}
	}
}