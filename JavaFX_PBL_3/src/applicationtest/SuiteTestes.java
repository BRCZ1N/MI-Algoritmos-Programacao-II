package applicationtest;

import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SelectClasses;


@Suite
@SelectClasses({
      DaoFornecedoresTest.class,
      DaoPratoTest.class,
      DaoProdutosTest.class,
	  DaoUsuariosTest.class,
      DaoVendasTest.class,
    })
public class SuiteTestes {}
