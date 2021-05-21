
/**
 * 在这里给出对类 DirectorsFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class DirectorsFilter implements Filter{
    private String[] myDirector;
    public DirectorsFilter(String director){
        myDirector = director.split(",");
    }
    
    public boolean satisfies(String id){
        for(int i = 0; i < myDirector.length; i++){
            if(MovieDatabase.getDirector(id).contains(myDirector[i])){
                return true;
            }
        }
        return false;
    }
}
