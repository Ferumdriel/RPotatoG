/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;

/**
 *
 * @author BotNaEasyEnv
 */

/*
*Pokazówka jak używać
*/
public class DBTest extends BaseObject{

    private String testColumn;
    
    public static List<DBTest> loadAll(DB db) throws Exception{
        return  loadAllWithCondition(DBTest.class, "true", db);
    }
    
    public DBTest() {
    }

    public DBTest(long id) {
        super(id);
    }
    
    @Override
    public String getTableName() {
        return "test_table";
    }

    @Override
    public String getHumanName() {
        return "to jest tes";
    }

    public String getTestColumn() {
        return testColumn;
    }

    public void setTestColumn(String testColumn) {
        this.testColumn = testColumn;
    }

    @Override
    public ParametersBuilder saveOrUpdateParameters() {
        return new ParametersBuilder(
                getTestColumn()
        );
    }
    
}