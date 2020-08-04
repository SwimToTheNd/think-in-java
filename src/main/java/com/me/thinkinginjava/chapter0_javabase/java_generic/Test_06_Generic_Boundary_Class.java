package com.me.thinkinginjava.chapter0_javabase.java_generic;

/**
 * ���ڲ��Ժ��߽�ķ����� {@link CarFactory2}
 * @author BloodFly
 *
 */
public class Test_06_Generic_Boundary_Class {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// CarFactory<T extends Car>
		// ����Ϊ����Car
		CarFactory2<Car> carFactory2 = new CarFactory2<Car>();
		carFactory2.create(Car.class);
		// ����Car������BenzCar
		CarFactory2<BenzCar> benzCarFactory2 = new CarFactory2<BenzCar>();
		benzCarFactory2.create(BenzCar.class);
		// ����Car������BMWCar
		CarFactory2<BMWCar> bmwCarFactory2 = new CarFactory2<BMWCar>();
		bmwCarFactory2.create(BMWCar.class);
		// �Ǹ�����������
//		CarFactory2<Computer> wrongCarFactory2 = new CarFactory2<Computer>();
	}

}
