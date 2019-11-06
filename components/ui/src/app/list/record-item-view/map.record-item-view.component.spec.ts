import {makeRfi} from '../../../test_helpers/utilities'
import {MockRfiService} from '../../../test_helpers/mockRfiService'
import {MockPrismService} from '../../../test_helpers/mockPrismService'
import {Store, StoreModule} from '@ngrx/store'
import {AppState} from '../../state/appstate'
import {ComponentFixture, TestBed} from '@angular/core/testing'
import {RfiService} from '../../services/rfi.service'
import {PrismService} from '../../services/prism.service'
import {FormsModule} from '@angular/forms'
import {activeRecordReducer} from '../../state/reducers'
import {CUSTOM_ELEMENTS_SCHEMA, SimpleChange} from '@angular/core'
import {MapRecordItemViewComponent} from './map-record-item-view.component'
import {ChangeTabAction} from '../../state/actions'
import {ListService} from "../../services/list.service";
import Spy = jasmine.Spy;
import createSpyObj = jasmine.createSpyObj;


describe('MapRecordItemViewComponent', () => {

    let component: MapRecordItemViewComponent
    let fetchRfisSpy: Spy
    let fetchNominationsSpy: Spy
    let mockRfiService: MockRfiService
    let mockPrismService: MockPrismService
    let mockListService: Spy
    let store: Store<AppState>
    let fixture: ComponentFixture<MapRecordItemViewComponent>
    let dispatchSpy: Spy

    beforeEach(async () => {
        mockRfiService = new MockRfiService()
        mockPrismService = new MockPrismService()
        mockListService = createSpyObj('listService', ['saveRecord'])

        TestBed.configureTestingModule({
            declarations: [MapRecordItemViewComponent],
            providers: [
                {provide: RfiService, useValue: mockRfiService},
                {provide: PrismService, useValue: mockPrismService},
                {provide: ListService, useValue: mockListService},
            ],
            imports: [
                FormsModule,
                StoreModule.forRoot({
                    activeRecord: activeRecordReducer
                })
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        })

        store = TestBed.get(Store)
        fetchRfisSpy = spyOn(mockRfiService, 'fetchRfis').and.callThrough()
        fetchNominationsSpy = spyOn(mockPrismService, 'fetchNominations').and.callThrough()
        fixture = TestBed.createComponent(MapRecordItemViewComponent)
        dispatchSpy = spyOn(store, 'dispatch').and.callThrough()
        component = fixture.componentInstance
    })

    describe('ngOnChanges', () => {
        it('sets isActive to true if activeRecordIds contains the record id', () => {
            component.record = makeRfi({id: 2})
            component.ngOnChanges({activeRecordIds: new SimpleChange(null, ['2'], null)})
            expect(component.isActive).toBeTruthy()
        })

        it('sets isActive to false if activeRecordIds does not contains the record id', () => {
            component.record = makeRfi({id: 2})
            component.ngOnChanges({activeRecordIds: new SimpleChange(null, ['3'], null)})
            expect(component.isActive).toBeFalsy()
        })
    })

    it('watches for changes to the current nav', () => {
        component.record = makeRfi({id: 2})
        component.onTabSelect('LIST');
        expect(dispatchSpy).toHaveBeenCalledWith(new ChangeTabAction('LIST'))
    })
})
