import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ArffLoader;

public class demo {

    public static void main(String[] args) throws Exception {

        Classifier m_classifier = new RandomForest();
        File inputFile = new File("F:\\tableFile\\大三上各课程资源\\人工智能\\实验三\\tree.arff");//训练文件
        ArffLoader atf = new ArffLoader();
        atf.setFile(inputFile);
        Instances instancesTrain = atf.getDataSet(); // 读入训练文件
        inputFile = new File("F:\\tableFile\\大三上各课程资源\\人工智能\\实验三\\treeTest.arff");//测试文件
        atf.setFile(inputFile);
        Instances instancesTest = atf.getDataSet(); // 读入测试文件
        instancesTest.setClassIndex(instancesTest.numAttributes()-1); //设置分类属性所在行号（第一行为0号），instancesTest.numAttributes()可以取得属性总数
        double sum = instancesTest.numInstances(),//测试语料实例数
                right = 0.0f;
        instancesTrain.setClassIndex(instancesTrain.numAttributes()-1);
        m_classifier.buildClassifier(instancesTrain); //训练
        System.out.println(m_classifier);

        // 保存模型
        SerializationHelper.write("LibSVM.model", m_classifier);//参数一为模型保存文件，classifier4为要保存的模型

        for(int  i = 0;i<sum;i++)//测试分类结果  1
        {
            if(m_classifier.classifyInstance(instancesTest.instance(i))==instancesTest.instance(i).classValue())//如果预测值和答案值相等（测试语料中的分类列提供的须为正确答案，结果才有意义）
            {
                right++;//正确值加1
            }
        }

        // 获取上面保存的模型
        Classifier classifier8 = (Classifier) weka.core.SerializationHelper.read("LibSVM.model");
        double right2 = 0.0f;
        for(int  i = 0;i<sum;i++)//测试分类结果  2 (通过)
        {
            if(classifier8.classifyInstance(instancesTest.instance(i))==instancesTest.instance(i).classValue())//如果预测值和答案值相等（测试语料中的分类列提供的须为正确答案，结果才有意义）
            {
                right2++;//正确值加1
            }
        }
        double value=classifier8.classifyInstance(instancesTest.instance(0));
        String prediction=instancesTest.classAttribute().value((int)value);
        System.out.println("预测值:"+prediction);

        System.out.println(right);
        System.out.println(right2);
        System.out.println(sum);
        System.out.println("RandomForest classification precision:"+(right/sum));

        Evaluation classifierEval=new Evaluation(instancesTest);
        classifierEval.evaluateModel(m_classifier, instancesTest);

        System.out.println(classifierEval.toSummaryString("\nResults\n\n", true));
    }
}

