/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.math.BigDecimal;
import javax.ejb.Remote;

/**
 *
 * @author Arles Mathieu
 */
@Remote
public interface ConverterRemote {

    BigDecimal dollarToYen(BigDecimal dollars);

    BigDecimal yenToEuro(BigDecimal yen);
    
}
