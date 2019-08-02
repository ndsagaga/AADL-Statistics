package com.nirup.aadl.statistics;

import java.util.List;

import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.EndToEndFlow;
import org.osate.aadl2.Feature;
import org.osate.aadl2.FlowSpecification;
import org.osate.aadl2.impl.DataPortImpl;
import org.osate.aadl2.impl.SystemImplementationImpl;
import org.osate.aadl2.impl.SystemTypeImpl;
import org.osate.aadl2.modelsupport.modeltraversal.AadlProcessingSwitch;
import org.osate.aadl2.util.Aadl2Switch;

public class Statistics extends AadlProcessingSwitch {
	private int systemComponentCount,systemImpCount,systemFeatureCount,systemFeaturePortCount;

	protected Statistics() {
		super();
	}

	@Override
	protected void initSwitches() {
		aadl2Switch = new Aadl2Switch<String>() {

			public String caseComponentType(ComponentType obj) {
				if (obj instanceof SystemTypeImpl) {
					systemComponentCount++;
					List<Feature> temp = obj.getAllFeatures();
					for (Feature c:temp) {
						systemFeatureCount++;
						if (c instanceof DataPortImpl) {
							systemFeaturePortCount++;
						}
					}
				}
				return DONE;
			}

			public String caseComponentImplementation(ComponentImplementation ci) {
				if (ci instanceof SystemImplementationImpl) {
					systemImpCount++;
				}
				return DONE;
			}
			
			public String caseFlowSpecification(FlowSpecification obj) {

				return DONE;
			}

			public String caseEndToEndFlow(EndToEndFlow obj) {

				return DONE;
			}
		};
	}

	public String getOutput() {
		return  systemComponentCount+" system type declarations\n"
				+systemImpCount+" system implementation declarations\n"
				+systemFeatureCount+" total features ["+systemFeaturePortCount+" ports]";
	}
}
