/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Prezent.db;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author BotNaEasyEnv
 */

/*
*Klasa która konwertuje typy z Javy do typów BD
*Trzeba będzie zapewne poszerzyć o inne typy np. tablicowe jeśli będzie trzeba
*/
public class ParametersBuilder {

    private Object[] args;
    public ParametersBuilder(Object... args) {
        this.args = args;
    }
    
    public String getValueOfParameters(){
        String parameters = "";
        StringBuilder builder = new StringBuilder(parameters);
        for(int i=0;i<args.length;i++){
            builder.append(getSQLRecordForObject(args[i]));
            if(args.length-1!=i){
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return getValueOfParameters();
    }
    
    
    public String getSQLRecordForObject(Object object){
        String result;
        if(object == null){
            result ="null";
        }else if(object instanceof ParametersBuilder){
            result =((ParametersBuilder) object).getValueOfParameters();
        }else if(object instanceof String){
            result = "E'"+object+"'";
        }else if(object instanceof Date){
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
            String date = simple.format(object);
            result = "'"+date+"'::date";
        }else if(object instanceof Timestamp){
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = simple.format(object);
            result = "'"+timestamp+"'::timestamp";
        }else{
            result = ""+object+"";
        }
        return result;
    }

    public Object[] getArgs() {
        return args;
    }
    
    
}
