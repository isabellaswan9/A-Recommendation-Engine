
/**
 * 在这里给出对类 MinutesFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class MinutesFilter implements Filter{
    private int minMinutes;
    private int maxMinutes;
    
    public MinutesFilter(int min, int max){
        minMinutes = min;
        maxMinutes = max;
    }
    
    public boolean satisfies(String id){
        int currMinutes = MovieDatabase.getMinutes(id);
        return currMinutes >= minMinutes && currMinutes <= maxMinutes;
    }
}
