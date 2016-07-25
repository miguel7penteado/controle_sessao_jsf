package beans;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

//import javax.inject.Named;
//import javax.inject.Inject;
//import javax.enterprise.context.SessionScoped;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

//Named
//@SessionScoped
@ManagedBean   
@SessionScoped
public class ClasseContadora implements Serializable 
{
    
	 //@Inject
	@ManagedProperty("#{objetoAlocadoSessao}")
    private ObjetoAlocadoSessao objetoAlocadoSessao;

    private static final Logger LOG = Logger.getLogger(ClasseContadora.class.getName());

    private int count;
    
  
	private FacesContext contexto;
    private HttpSession sessao; 
	private String sessao_id;
	private ExternalContext contexto_externo ;
	private String cookie;

    public ClasseContadora() {
        LOG.info("Classe contadora - inicializando contador no construtor ...");
        count = 0; // vai ser sobreescrito pelo método init()
        this.contexto = FacesContext.getCurrentInstance();
        this.sessao = (HttpSession) this.contexto.getExternalContext().getSession(false);
        this.sessao_id = this.sessao.getId();
        this.contexto_externo = this.contexto.getExternalContext();
        
        Enumeration e = this.sessao.getAttributeNames();
        while (e.hasMoreElements())
        {
          String atributo_servlet = (String)e.nextElement();
          System.out.println("      attr  = "+ atributo_servlet);
          Object valor_atributo = this.sessao.getValue(atributo_servlet);
          System.out.println("      value = "+ valor_atributo);
        }

    }
    
    @PostConstruct
    public void init(){
        LOG.info("Classe contadora - inicializando contador no @PostConstruct ...");
        count = objetoAlocadoSessao.getInit();
        
        Cookie[] cookies =  ((HttpServletRequest)this.contexto_externo.getRequest()).getCookies(); 

		//reset session string
		this.cookie = null; 
		if
		(cookies != null) {
		 for (Cookie brezel : cookies) 
		 {
		     if (brezel.getName().equalsIgnoreCase("JSESSIONID")) 
		     {
		    	 this.cookie = brezel.getValue();
		    	 break;
		     }
		  } 
		} 

    }

    public void countActionVoid() {
        LOG.info("CountBean#countActionVoid() - Increasing counter ...");
        count++;
        
        this.contexto = FacesContext.getCurrentInstance();
        this.sessao = (HttpSession) this.contexto.getExternalContext().getSession(false);
        this.sessao_id = this.sessao.getId();
        Enumeration e = this.sessao.getAttributeNames();
        while (e.hasMoreElements())
        {
          String attr = (String)e.nextElement();
          System.out.println("      attr  = "+ attr);
          Object value = this.sessao.getValue(attr);
          System.out.println("      value = "+ value);
          System.out.println("Valor atual da sessao: "+ this.sessao_id );
        }
        
    }
    
    public String countActionAndForward() {
        LOG.info("CountBean#countActionAndForward() - Increasing counter ...");
        count++;
        return "count";
    }
    
    public String sair() 
    {
        LOG.info("CountBean#countActionAndRedirect() - Increasing counter ...");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        return "index.xhtml?faces-redirect=true;";
    }

   

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
    public void setObjetoAlocadoSessao(ObjetoAlocadoSessao objetoAlocadoSessao) {
        this.objetoAlocadoSessao = objetoAlocadoSessao;
    } 
    
    public FacesContext getContexto() {
  		return contexto;
  	}

  	public void setContexto(FacesContext cotexto) {
  		this.contexto = cotexto;
  	}

  	public HttpSession getSessao() {
  		return sessao;
  	}

  	public void setSessao(HttpSession sessao) {
  		this.sessao = sessao;
  	}
    
  	public String getSessao_id() {
		return sessao_id;
	}

	public void setSessao_id(String sessao_id) {
		this.sessao_id = sessao_id;
	}

	public ExternalContext getContexto_externo() {
		return contexto_externo;
	}

	public void setContexto_externo(ExternalContext contexto_externo) {
		this.contexto_externo = contexto_externo;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

}
