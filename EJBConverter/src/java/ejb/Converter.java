/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.math.BigDecimal;
import javax.ejb.Stateless;

/**
 *
 * @author Arles Mathieu
 */
@Stateless
public class Converter implements ConverterRemote, ConverterLocal {

    @Override
    public BigDecimal dollarToYen(BigDecimal dollars) {
        BigDecimal yenRate = new java.math.BigDecimal("121.6000");
        BigDecimal result = dollars.multiply(yenRate);
        return result.setScale(2, BigDecimal.ROUND_UP);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public BigDecimal yenToEuro(BigDecimal yen) {
        BigDecimal euroRate = new BigDecimal("0.0077");
        BigDecimal result = yen.multiply(euroRate);
        return result.setScale(2, BigDecimal.ROUND_UP);
    }

}
