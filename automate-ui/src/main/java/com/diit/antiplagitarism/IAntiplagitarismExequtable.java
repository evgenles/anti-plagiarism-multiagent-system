package com.diit.antiplagitarism;

import com.sun.org.apache.xpath.internal.operations.Bool;

public interface IAntiplagitarismExequtable {
    Boolean Install();
    StartData StartWork(String patchToAnalyze);
    UpdateInfo CheckUpdates();
    double GetProcessPercentage();
    ResultData GetResult();
    StartData Cancel();
}
