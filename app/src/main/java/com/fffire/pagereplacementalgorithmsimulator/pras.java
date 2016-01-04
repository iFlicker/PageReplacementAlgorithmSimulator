package com.fffire.pagereplacementalgorithmsimulator;

/**
 * Created by flicker on 16-1-3.
 */
public class pras {

    //传入物理块数 页面数 页面走向 , 分析并输出结果

    private String show_OPT = "";                               //----------------  //
    private String show_FIFO = "";                              //     初始化       //
    private String show_LRU = "";                               //四个算法字符串变量 //
    private String show_Clock = "";                             //----------------//
    private int BlockSize = 10;
    private int PageSize = 100;
    private int noPageCount = 0;                                //缺页次数
    private int page[] = new int[PageSize];				        //页面数组
    private int block[] = new int[BlockSize];				    //物理块数组
    private int pSize = 0;							            //用户输入页面数
    private int bSize = 0;							            //用户输入物理块数
    private int result[][] = new int[PageSize][BlockSize];		//存放页面和物理块数组
    private int blockFlag[] = new int[BlockSize];			    //intFlag判断该换出的页面

    public pras(){

    }
    public pras (int bSize, int pSize, int page[]){
        //带参构造方法,传入数据
        initPage();
        this.bSize = bSize;
        this.pSize = pSize;
        this.page = page;

        initBlockResult();
        //计算
        OPT();
        FIFO();
        LRU();
        clock();
    }


    public String show(String va){
        //按参数返回相应方法结果
        if (va.equals("OPT")){
            return show_OPT;
        }
        else if(va.equals("FIFO")){
            return show_FIFO;
        }
        else if(va.equals("LRU")){
            return show_LRU;
        }
        else if(va.equals("Clock")){
            return show_Clock;
        }else
            return "parameter error";
    }


    //初始化页面数组
    public void initPage(){
        for(int i = 0;i<PageSize;i++) {
            page[i] = -1;
        }
    }

    //初始化物理块和Result数组
    public void initBlockResult(){
        for(int i = 0;i<BlockSize;i++) {
            block[i] = -1;
        }
        for(int i = 0;i < PageSize;i++) {
            for(int j = 0; j < BlockSize; j++) {
                result[i][j] = -1;
            }
        }
    }

    //判断物理块中是否存在要调用的页面
    public int is_Exist(int i) {
        for(int j = 0;j < bSize; j++) {
            if (block[j] == i) {
                return j;
            }
        }
        return -1;
    }

    //最佳置换算法(OPT)
    void OPT(){
        int position = 0,noPageCount = 0,j = 0;
        int pageFlag = 0,resultFlag = 0;
        for(int i = 0;i < BlockSize;i++)
            blockFlag[i] = 0;
        while(pageFlag < pSize){
            if(is_Exist(page[pageFlag]) != -1) { //判断页面是否已经存在
                resultFlag++;
            } else {
                if(position < bSize) {                  //判断有无空闲物理块
                    block[position] = page[pageFlag];   //若有则将页面放入空闲块
                    position++;noPageCount++;
                    for(int i = 0;i < position;i++)
                        result[resultFlag][i] = block[i];
                    resultFlag++;
                } else {
                    for(int i = 0;i < bSize;i++){
                        for(j = pageFlag+1;j < pSize;j++){
                            if(block[i] == page[j]){
                                blockFlag[i] = j;
                                break;
                            }
                        }
                        if(j == pSize)
                            blockFlag[i] = 999;
                    }
                    int optPos = 0,max = blockFlag[0];
                    for(int i = 0;i < bSize;i++)
                        if(max < blockFlag[i]) {
                            max = blockFlag[i];
                            optPos = i;
                        }
                    block[optPos] = page[pageFlag];
                    noPageCount++;
                    for(int i = 0;i < bSize;i++)
                        result[resultFlag][i] = block[i];
                    resultFlag++;
                }
            }
            pageFlag++;
        }
        System.out.println("最佳置换算法(OPT):");

        //---------------------------------------------------
        //输出到show相应字符串
        for(int i =0 ;i < pSize;i++) {
            System.out.print(" " + page[i] + "→ ");
            show_OPT += " " + page[i] + "→ ";            //输出到字符串
            for(int x = 0;x < bSize;x++){
                if(result[i][x] == -1)
                    break;
                else {
                    System.out.print("<" + result[i][x] + ">" + " ");
                    show_OPT += "<" + result[i][x] + ">" + " ";         //输出到字符串
                }
            }
            System.out.println();
            show_OPT += "\n";       //输出到字符串
        }
        System.out.println("缺页次数: " + noPageCount);
        show_OPT += "缺页次数: " + noPageCount + "\n";                          //输出到字符串
        System.out.println("缺 页 率:  " + ((double)noPageCount/pSize)*100 + "%" );
        show_OPT += "缺 页 率:  " + ((double)noPageCount/pSize)*100 + "%" + "\n";    //输出到字符串
        //-----------------------------------------------------------
    }

    //先进先出页面置换算法(FIFO)
    void FIFO() {
        int j = 0,noPageCount = 0,position = 0;//指示物理块，查找有无空闲
        int blockFlag = 0,pageFlag = 0,resultFlag = 0;//物理块标记，确定该换出的物理块下标
        while (pageFlag < pSize){
            if(is_Exist(page[pageFlag]) != -1){
                resultFlag++;
            } else {
                if(position < bSize){
                    block[position] = page[pageFlag];
                    position++;
                    noPageCount++;
                    for(int i = 0;i <= position;i++)
                        result[resultFlag][i] = block[i];
                    resultFlag++;
                } else {
                    block[blockFlag] = page[pageFlag];
                    noPageCount++;
                    for(int i = 0;i < bSize;i++)
                        result[resultFlag][i] = block[i];
                    resultFlag++;
                    blockFlag++;
                    blockFlag = blockFlag % bSize;
                }
            }
            pageFlag++;
        }
        System.out.println("先进先出（FIFO）置换算法:");
        //---------------------------------------------------
        //输出到show相应字符串
        for(int i =0 ;i < pSize;i++) {
            System.out.print(" " + page[i] + "→ ");
            show_FIFO += " " + page[i] + "→ ";            //输出到字符串
            for(int x = 0;x < bSize;x++){
                if(result[i][x] == -1)
                    break;
                else {
                    System.out.print("<" + result[i][x] + ">" + " ");
                    show_FIFO += "<" + result[i][x] + ">" + " ";         //输出到字符串
                }
            }
            System.out.println();
            show_FIFO += "\n";       //输出到字符串
        }
        System.out.println("缺页次数: " + noPageCount);
        show_FIFO += "缺页次数: " + noPageCount + "\n";                          //输出到字符串
        System.out.println("缺 页 率:  " + ((double)noPageCount/pSize)*100 + "%" );
        show_FIFO += "缺 页 率:  " + ((double)noPageCount/pSize)*100 + "%" + "\n";    //输出到字符串
        //-----------------------------------------------------------

    }

    //最近最久未用算法(LRU)
    void LRU(){
        int pageFlag = 0,resultFlag = 0,position = 0,noPageCount = 0;
        for(int i = 0;i < BlockSize;i++)            //初始化标记数组
            blockFlag[i] = 0;
        while(pageFlag < pSize){
            if(is_Exist(page[pageFlag]) != -1){     //判断页面是否已经在
                resultFlag++;
                blockFlag[is_Exist(page[pageFlag])] = 0;    //若在则将标记数组对应位置设为0
            } else {
                if(position < bSize){
                    block[position] = page[pageFlag];
                    blockFlag[position] = 0;
                    position++;
                    noPageCount++;
                    for(int i = 0;i <= position;i++)
                        result[resultFlag][i] = block[i];
                    resultFlag++;
                } else {
                    int last = 0,min = blockFlag[0];
                    for(int i = 0;i < bSize;i++) {
                        if (min < blockFlag[i]) {
                            min = blockFlag[i];
                            last = i;
                        }
                    }
                    block[last] = page[pageFlag];   //置换最久未用页面
                    blockFlag[last] = 0;
                    noPageCount++;
                    for(int i = 0;i < bSize;i++)
                        result[resultFlag][i] = block[i];
                    resultFlag++;
                }
            }
            for(int i = 0;i < bSize;i++)
                blockFlag[i]++;
            pageFlag++;
        }
        System.out.println("最近最久未用（LRU）置换算法:");
        //---------------------------------------------------
        //输出到show相应字符串
        for(int i =0 ;i < pSize;i++) {
            System.out.print(" " + page[i] + "→ ");
            show_LRU += " " + page[i] + "→ ";            //输出到字符串
            for(int x = 0;x < bSize;x++){
                if(result[i][x] == -1)
                    break;
                else {
                    System.out.print("<" + result[i][x] + ">" + " ");
                    show_LRU += "<" + result[i][x] + ">" + " ";         //输出到字符串
                }
            }
            System.out.println();
            show_LRU += "\n";       //输出到字符串
        }
        System.out.println("缺页次数: " + noPageCount);
        show_LRU += "缺页次数: " + noPageCount + "\n";                          //输出到字符串
        System.out.println("缺 页 率:  " + ((double)noPageCount/pSize)*100 + "%" );
        show_LRU += "缺 页 率:  " + ((double)noPageCount/pSize)*100 + "%" + "\n";    //输出到字符串
        //-----------------------------------------------------------
    }

    //简单时钟置换算法(clock)(NRU)
    void clock() {
        int position = 0,noPageCount = 0,flag = 0;
        int pageFlag = 0,resultFlag = 0;
        boolean bblockFlag[] = new boolean[BlockSize]; //用布尔数组
        while(pageFlag < pSize) {
            if(is_Exist(page[pageFlag]) != -1){
                resultFlag++;
                bblockFlag[is_Exist(page[pageFlag])] = true;
            } else {
                if(position < bSize){
                    block[position] = page[pageFlag];
                    noPageCount++;
                    bblockFlag[position] = true;
                    position++;
                    for(int i = 0;i < position;i++)
                        result[resultFlag][i] = block[i];
                    resultFlag++;
                } else {
                    while(true){    //找出访问位为false的页面并置反
                        if(bblockFlag[flag] == false) break;
                        bblockFlag[flag] = false;
                        flag++;
                        flag = flag%bSize;
                    }
                    block[flag] = page[pageFlag];
                    bblockFlag[flag] = true;
                    flag++;
                    flag = flag%bSize;
                    noPageCount++;
                    for(int i = 0;i < position;i++)
                        result[resultFlag][i] = block[i];
                    resultFlag++;
                }
            }
            pageFlag++;
        }
        System.out.println("简单时钟(clock)置换算法:");
        //---------------------------------------------------
        //输出到show相应字符串
        for(int i =0 ;i < pSize;i++) {
            System.out.print(" " + page[i] + "→ ");
            show_Clock += " " + page[i] + "→ ";            //输出到字符串
            for(int x = 0;x < bSize;x++){
                if(result[i][x] == -1)
                    break;
                else {
                    System.out.print("<" + result[i][x] + ">" + " ");
                    show_Clock += "<" + result[i][x] + ">" + " ";         //输出到字符串
                }
            }
            System.out.println();
            show_Clock += "\n";       //输出到字符串
        }
        System.out.println("缺页次数: " + noPageCount);
        show_Clock += "缺页次数: " + noPageCount + "\n";                          //输出到字符串
        System.out.println("缺 页 率:  " + ((double)noPageCount/pSize)*100 + "%" );
        show_Clock += "缺 页 率:  " + ((double)noPageCount/pSize)*100 + "%" + "\n";    //输出到字符串
        //-----------------------------------------------------------
    }

}
