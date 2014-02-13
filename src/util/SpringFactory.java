package util;

import org.springframework.beans.BeansException;  
import org.springframework.context.ApplicationContext;  
import org.springframework.context.ApplicationContextAware;  
public class SpringFactory implements ApplicationContextAware{  
  
    private static ApplicationContext context;  
      
    
	@Override  
    public void setApplicationContext(ApplicationContext applicationContext)  
            throws BeansException {  
//        this.setContext(applicationContext);
		SpringFactory.setContext(applicationContext);
    }  
      
  
    public static Object getObject(String id) {  
        Object object = null;  
        object = getContext().getBean(id);  
        return object;  
    }


	public static ApplicationContext getContext() {
		return context;
	}
	public static void setContext(ApplicationContext context) {
		SpringFactory.context = context;
	}  
}  