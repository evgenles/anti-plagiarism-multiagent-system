package com.diit.antiplagiarism.environment;

import com.diit.antiplagiarism.environment.IPhysicSpace;
import com.diit.antiplagiarism.environment.NetworkSpace;
import io.janusproject.services.distributeddata.DistributedDataStructureService;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.SpaceID;
import io.sarl.lang.core.SpaceSpecification;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class NetworkSpaceSpecification implements SpaceSpecification<IPhysicSpace> {
  @Inject
  private DistributedDataStructureService factory;
  
  public IPhysicSpace create(final SpaceID id, final Object... params) {
    return new NetworkSpace(id, this.factory);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
  
  @SyntheticMember
  public NetworkSpaceSpecification() {
    super();
  }
}
