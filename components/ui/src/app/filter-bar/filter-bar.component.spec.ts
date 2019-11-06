import {ComponentFixture, TestBed} from '@angular/core/testing'
import {FilterBarComponent} from './filter-bar.component'
import {MockHttpClient} from '../../test_helpers/mockHttpClient'
import {HttpClient} from '@angular/common/http'
import {recordSelectOptionsReducer} from '../state/reducers'
import {Store, StoreModule} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {ClickOutsideModule} from 'ng-click-outside'
import {ReceivedSelectOptions} from '../state/actions'
import {defaultSystemSelectOptions, SystemSelectOptions} from '../models/select-option.model'
import {TitleCasePipe} from '@angular/common'
import {MockRecordService} from '../../test_helpers/mockRecordService'
import {RecordService} from '../services/record.service'
import {FilterService} from '../services/filter.service'
import {FilterComponent} from './filter/filter.component'
import {FilterSearchComponent} from './filter-search/filter-search.component'
import {FormsModule} from '@angular/forms'
import {ClearableFilterComponent} from './filter/clearable-filter.component'
import {RecordType} from "../models/record.model";
import {MockFilterService} from "../../test_helpers/mockFilterService";
import {FilterSystemComponent} from "./filter-system/filter-system.component";
import {FilterSystemOptionComponent} from "./filter-system/filter-system-option/filter-system-option.component";

describe('FilterBarComponent', () => {
    let component: FilterBarComponent
    let fixture: ComponentFixture<FilterBarComponent>
    let store: Store<AppState>
    let mockRfiService: MockRecordService
    let mockFilterService: MockFilterService
    let mockClearableFilterComponents

    beforeEach(() => {
        const mockHttpClient = new MockHttpClient()
        mockRfiService = new MockRecordService()

        mockFilterService = new MockFilterService()

        mockClearableFilterComponents = jasmine.createSpyObj<ClearableFilterComponent>('filterComponent', ['clearCallback'])

        TestBed.configureTestingModule({
            declarations: [FilterBarComponent, FilterComponent, FilterSearchComponent, FilterSystemComponent, FilterSystemOptionComponent],
            providers: [
                {provide: HttpClient, useValue: mockHttpClient},
                {provide: RecordService, useValue: mockRfiService},
                {provide: FilterService, useValue: mockFilterService},
                TitleCasePipe
            ],
            imports: [
                ClickOutsideModule,
                FormsModule,
                StoreModule.forRoot({
                    recordSelectOptions: recordSelectOptionsReducer
                })],
        })

        store = TestBed.get(Store)
        mockRfiService = TestBed.get(RecordService)
        fixture = TestBed.createComponent(FilterBarComponent)
        component = fixture.componentInstance
    })

    describe('initialization', () => {
        it('initializes the options lists', () => {
            let systemSelectOptions: SystemSelectOptions[] = defaultSystemSelectOptions()

            //status options
            systemSelectOptions[0].data.statusOptions = [{id: 1, value: 'IMM STATUS 1'}]
            systemSelectOptions[1].data.statusOptions = [{id: 2, value: 'PRISM STATUS 1'}]

            //priority options
            systemSelectOptions[0].data.priorityOptions = [{id: 1, value: 'IMM PRIORITY 1'}]
            systemSelectOptions[1].data.priorityOptions = [{id: 2, value: 'PRISM PRIORITY 1'}]

            store.dispatch(new ReceivedSelectOptions(systemSelectOptions))

            expect(component.statusOptions).toEqual([
                {displayName: 'RFI', options: [{'id': 1, 'value': 'IMM STATUS 1', 'system': RecordType.RFI}]},
                {displayName: 'Nomination', options: [{'id': 2, 'value': 'PRISM STATUS 1', 'system': RecordType.NOMINATION}]}
            ])
            expect(component.priorityOptions).toEqual([
                {displayName: 'RFI', options: [{'id': 1, 'value': 'IMM PRIORITY 1', 'system': RecordType.RFI}]},
                {displayName: 'Nomination', options: [{'id': 2, 'value': 'PRISM PRIORITY 1', 'system': RecordType.NOMINATION}]}
            ])
            expect(component.systemOptions).toEqual([
                {
                    displayName: 'COMPONENTS',
                    options: [
                        {id: 0, value: 'IMM RFIs', system: RecordType.RFI},
                        {id: 1, value: 'PRISM Nominations', system: RecordType.NOMINATION}
                    ]
                }
            ])
            expect(component.targetOptions).toEqual([
                {
                    displayName: 'TARGET',
                    options: [
                        {id: 0, value: 'POINT'},
                        {id: 1, value: 'LOC'},
                        {id: 2, value: 'DSA'},
                        {id: 3, value: 'AREA'},
                    ]
                }
            ])
        })

        it('retrieves IMM RFI options', () => {
            const rfiFetchSelectOptionsSpy = spyOn(mockRfiService, 'fetchRecordSelectOptions')
            component.ngOnInit()
            expect(rfiFetchSelectOptionsSpy).toHaveBeenCalled()
        })
    })

    describe('hasFilters', () => {
        it('delegates and returns the value from filter service', function () {
            const hasFiltersSpy = spyOn(mockFilterService, 'hasFilters')
            hasFiltersSpy.and.returnValue(true)
            expect(component.hasFilters()).toBeTruthy()
            hasFiltersSpy.and.returnValue(false)
            expect(component.hasFilters()).toBeFalsy()
        })
    })

    describe('clearAllFilters', () => {
        beforeEach(() => {
            fixture.detectChanges()
        })

        it('should execute the clearCallback method of each of the ClearableFilterComponent children', () => {
            component.filterComponents.forEach(filterComponent => {
                spyOn(filterComponent, 'clearCallback')
            })

            component.clearAllFilters()

            component.filterComponents.forEach(filterComponent => {
                expect(filterComponent.clearCallback).toHaveBeenCalled()
            })
        })
    })
})
