// ASSIGNMENT 1 - PART 2 
// SAMPLE SOLUTION


class Delegation {
	
	public static void main(String args[]) {
		B b = new B();
		System.out.println(b.f()+b.g()+b.p(1)+b.q(2));
	
		D d = new D();
		System.out.println(d.f()+d.g()+d.h()+d.p(1)+d.q(2)+d.r());

		B2 b2 = new B2();
		System.out.println(b2.f()+b2.g()+b2.p(1)+b2.q(2));	
		
		D2 d2 = new D2();
		System.out.println(d2.f()+d2.g()+d2.h()+d2.p(1)+d2.q(2)+d2.r());	
		
	}
}
 
 class Delegation2 {
		
	public static void main(String args[]) {
		
		E e = new E();
		System.out.println(e.f()+e.g()+e.h()+e.p(1)+e.q(2)+e.r()+e.k(100));
		
		F f = new F();
		System.out.println(f.f()+f.g()+f.h()+f.p(1)+f.q(2)+f.r()+f.j(10)+f.l(100));

		E2 e2 = new E2();
		System.out.println(e2.f()+e2.g()+e2.h()+e2.p(1)+e2.q(2)+e2.r()+e2.k(100));

		F2 f2 = new F2();
		System.out.println(f2.f()+f2.g()+f2.h()+f2.p(1)+f2.q(2)+f2.r()+f2.j(10)+f2.l(100));			
	}
}
 
 
abstract class A {
	public static int a1 = 100;
	protected int a2 = 200;

	public int f() {
		return u(100) + v(200);
	}

	public int u(int m) {return m; }

    public int v(int m) {return m*2; }
}

class B extends A {
	public static int b1 = 1000;
	protected int b2 = 2000;

	public int g() {
		return this.p(100) + this.q(200); 
	}

	public int p(int m) {
		return m + a1+b1;
	}

	public int q(int m) {
		return m + a2+b2;
	}
}

abstract class C extends B {
	public static int c1 = 10000;
	protected int c2 = 20000;

	public int r() {
		return f() + g() + h();
	}

	public int q(int m) {
		return m + a2 + b2 + c2;
	}

	protected abstract int h();
}

class D extends C {
	public static int d1 = 100000;
	protected int d2 = 200000;

	public int r() {
		return f() + g() + this.h();
	}

	public int p(int m) {
		return super.p(m) + d2;
	}

	public int h() {
		return a1 + b1 + c1;
	}
	
	public int j(int n) {
		return r() + super.r();
	}

}

class E extends C {
	protected int e1 = 1;
	protected int e2 = 2;

	public int q(int m) {
		return p(m) + c2;
	}

	public int h() {
		return a1 + b1 + e1;
	}
	
	public int k(int n) {
		return q(n) + super.q(n);
	}

}

class F extends D {
	protected int f1 = 10;
	protected int f2 = 20;

	public int q(int m) {
		return p(m) + c1 + d1;
	}

	public int h() {
		return c2 + f2;
	}
	
	public int l(int n) {
		return q(n) + super.q(n);
	}
}


//============= TRANSLATION IN TERMS OF DELEGATION ===============

interface IA {
	int a1 = 100;
	default int f() {return u(100) + v(200);}
	static int u(int m) {return m;}
	static int v(int m) {return m*2;}
}

interface IB extends IA {
    int b1 = 1000;
	int g();
	default int p(int m) {
		return m + a1 + b1;
	}
	int q(int m);
}

interface IC extends IB {
    int c1 = 10000;
	int r();
	int h();
}

interface ID extends IC {
    int d1 = 100000;
	default int h() {return a1+ b1 + c1;}
	int j(int m);
}

interface IE extends IC {
	int k(int m);
}

interface IF extends ID {
	int l(int m);
}


class A2 implements IA {
	protected int a2 = 200;
	public A2(IA a){
		this2 = a;
	}
	IA this2;
}

class B2 implements IB {
	protected int b2 = 2000;
	A2 super2;
	IB this2;
	
	public B2() {
		super2 = new A2(this);
		this2 = this;
	}
	
	public B2(IB b){
		super2 = new A2(b);
		this2 = b;
	}
	
	public int g() {
		return this2.p(100) + this2.q(200); 
	}

	public int q(int m) {
		return m + super2.a2 + b2;
	}
}

class C2 implements IC {
	protected int c2 = 20000;
	B2 super2;
	IC this2;
	
	public C2(IC c){
		super2 = new B2(c);
		this2 = c;
	}

	public int r() {
		return this2.f() + this2.g() + this2.h();
	}

	public int q(int m) {
		return m + super2.super2.a2 + super2.b2 + c2;
	}

	public int h() {
		return this2.h();
	}

	// Delegation Methods
	public int g() {return super2.g();}
}

class D2 implements ID {
	protected int d2 = 200000;
	C2 super2;
	ID this2;
	
	public D2() {
		super2 = new C2(this);
		this2 = this;
	}
	
	public D2(ID d){
		super2 = new C2(d);
		this2 = d;
	}
	
	public int r() {
		return this2.f() + this2.g() + this2.h();
	}

	public int p(int m) {
		return super2.p(m) + d2;
	}
	
	public int j(int n) {
		return this2.r() + super2.r();
	}
	
	// Delegation Methods
	public int g() {return super2.g();}
	public int q(int m) {return super2.q(m);}
}

class E2 implements IE {
	protected int e1 = 1;
	protected int e2 = 2;
	C2 super2;
	
	public E2() {
		super2 = new C2(this);
	}

	public int q(int m) {
		return this.p(m) + super2.c2;
	}

	public int h() {
		return a1 + b1 + e1;
	}
	
	public int k(int n) {
		return this.q(n) + super2.q(n);
	}

	// Delegation Methods
	public int r() {return super2.r();}
	public int g() {return super2.g();}
	//public int p(int m) {return super2.p(m);}
}

class F2 implements IF {
	protected int f1 = 10;
	protected int f2 = 20;
	D2 super2;
	
	public F2() {
		super2 = new D2(this);
	}

	public int q(int m) {
		return p(m) + c1 + d1;
	}

	public int h() {
		return super2.super2.c2 + f2;
	}
	
	public int l(int n) {
		return this.q(n) + super2.q(n);
	}

	// Delegation Methods
	public int j(int m) {return super2.j(m);}
	public int r() {return super2.r();}
	public int g() {return super2.g();}
	public int p(int m) {return super2.p(m);}

}
 
