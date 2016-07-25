package beans;

import java.io.Serializable;

//import javax.enterprise.context.SessionScoped;
//import javax.inject.Named;

 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.SessionScoped;

//@Named
//@SessionScoped
@ManagedBean
@SessionScoped
public class ObjetoAlocadoSessao implements Serializable 
{

    private int init;

    public ObjetoAlocadoSessao() {
        init = 5;
    }

    public int getInit() {
        return init;
    }

    public void setInit(int init) {
        this.init = init;
    }

}
