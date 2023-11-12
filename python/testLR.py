import numpy as np


# 数据处理
def loadData(fileName):
    dataList = [];
    labelList = []
    fr = open(fileName, 'r')
    for line in fr.readlines():
        curLine = line.strip().split(',')
        labelList.append(float(curLine[2]))
        dataList.append([float(num) for num in curLine[0:1]])
    return dataList, labelList


# LR预测
def predict(w, x):
    wx = np.dot(w, x)
    P1 = np.exp(wx) / (1 + np.exp(wx))
    if P1 >= 0.5:
        return 1
    return 0


# 梯度下降训练
def GD(trainDataList, trainLabelList, iter=30):
    for i in range(len(trainDataList)):
        trainDataList[i].append(1)
    trainDataList = np.array(trainDataList)
    w = np.zeros(trainDataList.shape[1])
    alpha = 0.001
    for i in range(iter):
        for j in range(trainDataList.shape[0]):
            wx = np.dot(w, trainDataList[j])
            yi = trainLabelList[j]
            xi = trainDataList[j]
            w += alpha * (yi - (np.exp(wx)) / (1 + np.exp(wx))) * xi
    return w


# 测试
def test(testDataList, testLabelList, w):
    for i in range(len(testDataList)):
        testDataList[i].append(1)
    errorCnt = 0
    for i in range(len(testDataList)):
        if testLabelList[i] != predict(w, testDataList[i]):
            errorCnt += 1
    return 1 - errorCnt / len(testDataList)


# 打印准确率
if __name__ == '__main__':
    trainData, trainLabel = loadData('data/train.txt')
    testData, testLabel = loadData('data/test.txt')
    w = GD(trainData, trainLabel)
    accuracy = test(testData, testLabel, w)
    print('the accuracy is:', accuracy)
