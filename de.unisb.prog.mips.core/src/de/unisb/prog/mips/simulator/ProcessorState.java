package de.unisb.prog.mips.simulator;


public class ProcessorState {
	
	public int[] gp;
	public int pc, lo, hi;
	
	public ProcessorState clone() {
		return new ProcessorState() {
			{
				this.gp = ProcessorState.this.gp.clone();
				this.pc = ProcessorState.this.pc;
				this.lo = ProcessorState.this.lo;
				this.hi = ProcessorState.this.hi;
			}
		};
	}

}
