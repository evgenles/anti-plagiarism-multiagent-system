package com.diit.antiplagiarism.environment;

import com.diit.antiplagiarism.environment.IPhysicSpace;
import com.diit.antiplagiarism.environment.UUIDScope;
import io.janusproject.kernel.repository.UniqueAddressParticipantRepository;
import io.janusproject.kernel.space.AbstractEventSpace;
import io.janusproject.services.distributeddata.DMap;
import io.janusproject.services.distributeddata.DistributedDataStructureService;
import io.janusproject.services.network.NetworkService;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import io.sarl.lang.core.EventListener;
import io.sarl.lang.core.SpaceID;
import io.sarl.lang.util.SynchronizedCollection;
import io.sarl.lang.util.SynchronizedSet;
import io.sarl.util.Collections3;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Future;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class NetworkPhysicSpaceImpl extends AbstractEventSpace implements IPhysicSpace {
  private final DMap<UUID, Serializable> entities;
  
  private final String KEY_CREATORID = "creatorID";
  
  private final UniqueAddressParticipantRepository<UUID> agents;
  
  @Inject
  private NetworkService network;
  
  public NetworkPhysicSpaceImpl(final SpaceID id, final DistributedDataStructureService factory) {
    super(id, factory);
    String _string = id.toString();
    UniqueAddressParticipantRepository<UUID> _uniqueAddressParticipantRepository = new UniqueAddressParticipantRepository<UUID>((_string + "-antiplagiarism-agents"), factory);
    this.agents = _uniqueAddressParticipantRepository;
    String _string_1 = id.toString();
    this.entities = factory.<UUID, Serializable>getMap((_string_1 + "-physicObjects"));
  }
  
  @Override
  public SynchronizedSet<UUID> getParticipants() {
    synchronized (this.agents) {
      return Collections3.<UUID>unmodifiableSynchronizedSet(this.agents.getParticipantIDs());
    }
  }
  
  public UUID getCreatorID() {
    Serializable _get = this.entities.get(this.KEY_CREATORID);
    return ((UUID) _get);
  }
  
  public Future<?> fireAsync(final EventListener agent, final Event event) {
    com.diit.antiplagiarism.environment.AsyncRunner _asyncRunner = new com.diit.antiplagiarism.environment.AsyncRunner(agent, event);
    return this.executorService.submit(_asyncRunner);
  }
  
  public Boolean putOnEventBus(final Event event, final UUID scope) {
    synchronized (this.agents) {
      SynchronizedCollection<EventListener> _listeners = this.agents.getListeners();
      for (final EventListener agent : _listeners) {
        boolean _equals = scope.equals(agent.getID());
        if (_equals) {
          this.fireAsync(agent, event);
          return Boolean.valueOf(true);
        }
      }
      return Boolean.valueOf(false);
    }
  }
  
  public void putOnNetwork(final Event event, final UUID scope) {
    try {
      UUIDScope _uUIDScope = new UUIDScope(scope);
      this.network.publish(_uUIDScope, event);
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        this.logger.getKernelLogger().severe(
          String.format(
            "Cannot notify over the network; scope={0}, event={1}, exception={2}", scope, event, e));
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    NetworkPhysicSpaceImpl other = (NetworkPhysicSpaceImpl) obj;
    if (!Objects.equals(this.KEY_CREATORID, other.KEY_CREATORID)) {
      return false;
    }
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.KEY_CREATORID);
    return result;
  }
}
