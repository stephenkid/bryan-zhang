package org.poseidon.controller.base;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class GenericBindingInitializer implements WebBindingInitializer {

	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new PoseidonDateEditor(new DateFormat[]{new SimpleDateFormat("yyyy-MM-dd"),
																						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")}, 
																						true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	private class PoseidonDateEditor extends PropertyEditorSupport{
		private final DateFormat[] dateFormatArray;

		private final boolean allowEmpty;
		
		public PoseidonDateEditor(DateFormat[] dateFormatArray, boolean allowEmpty){
			this.dateFormatArray = dateFormatArray;
			this.allowEmpty = allowEmpty;
		}
		
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if (this.allowEmpty && !StringUtils.hasText(text)) {
				setValue(null);
			}else {
				for (DateFormat dateFormat : dateFormatArray){
					try{
						setValue(dateFormat.parse(text));
					}catch (ParseException ex) {
						continue;
					}
				}
			}
		}
	}
}
