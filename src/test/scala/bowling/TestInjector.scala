package bowling

import api.APIModule
import domain.DomainModule
import infrastructure.InfrastructureModule

class TestInjector extends DomainModule with APIModule with InfrastructureModule {

}
