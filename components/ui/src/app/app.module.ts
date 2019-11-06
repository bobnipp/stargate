import {BrowserModule} from '@angular/platform-browser'
import {NgModule} from '@angular/core'
import {AppComponent} from './app.component'
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http'
import {FormsModule, ReactiveFormsModule} from '@angular/forms'
import {RecordListViewComponent} from './list/record-list-view.component'
import {AgmCoreModule, LAZY_MAPS_API_CONFIG} from '@agm/core'
import {AgmSnazzyInfoWindowModule} from '@agm/snazzy-info-window'
import {MapComponent} from './map/map.component'
import {MapConfigService} from './services/map-config.service'
import {RfiService} from './services/rfi.service'
import {StoreModule} from '@ngrx/store'
import {NavigationComponent} from './navigation/navigation.component'
import {ContentComponent} from './content/content.component'
import {PrismService} from './services/prism.service'
import {SidebarComponent} from './sidebar/sidebar.component'
import {
    activeRecordReducer,
    activeRfiMainFormReducer,
    activeRfiSubFormReducer,
    confirmationReplyReducer,
    currentUserReducer,
    customListCreatedReducer,
    filteredNominationsReducer,
    filteredRfisReducer,
    mapConfigReducer,
    navigationReducer,
    prismNominationsReducer,
    receivedListReducer,
    receivedOpenLinksReducer,
    recordSelectOptionsReducer,
    rfiListReducer,
    showConfirmationModalReducer,
    showDndRecordDropsReducer,
    showToastReducer
} from './state/reducers'
import {SideNavigationComponent} from './side-navigation/side-navigation.component'
import {OWL_DATE_TIME_FORMATS, OwlDateTimeModule} from 'ng-pick-datetime'
import {OwlMomentDateTimeModule} from 'ng-pick-datetime-moment'
import {SidebarAboutComponent} from './sidebar/sidebar-about/sidebar-about.component'
import {SidebarCollectionComponent} from './sidebar/sidebar-collection/sidebar-collection.component'
import {SidebarTargetComponent} from './sidebar/sidebar-target/sidebar-target.component'
import {SidebarActivityComponent} from './sidebar/sidebar-activity/sidebar-activity.component'
import {UserService} from './services/user.service'
import {SidebarDetailsComponent} from './sidebar/sidebar-details/sidebar-details.component'
import {SidebarLinksComponent} from './sidebar/sidebar-links/sidebar-links.component'
import {FilterBarComponent} from './filter-bar/filter-bar.component'
import {ConfirmModalComponent} from './confirm-modal/confirm-modal.component'
import {CoordinatesService} from './services/coordinates.service'
import {ClickOutsideModule} from 'ng-click-outside'
import {FilterComponent} from './filter-bar/filter/filter.component'
import {SidebarMapListComponent} from './map/sidebar-map-list/sidebar-map-list.component'
import {WindowService} from './window/window.service'
import {environment} from '../environments/environment'
import {SidebarHistoryComponent} from './sidebar/sidebar-history/sidebar-history.component'
import {DatePickerComponent} from './common/date-picker/date-picker.component'
import {HashLocationStrategy, LocationStrategy, TitleCasePipe} from '@angular/common'
import {RecordService} from './services/record.service'
import {SidebarExistingLinkComponent} from './sidebar/sidebar-existing-link/sidebar-existing-link.component'
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import {FilterService} from './services/filter.service'
import {FilterSearchComponent} from './filter-bar/filter-search/filter-search.component'
import {MapService} from './services/map-service.service'
import {TargetPopoverComponent} from './sidebar/target-popover/target-popover.component'
import {BaseRecordItemViewComponent} from './list/record-item-view/base-record-item-view.component'
import {ListRecordItemViewComponent} from './list/record-item-view/list-record-item-view.component'
import {MapRecordItemViewComponent} from './list/record-item-view/map-record-item-view.component'
import {AddListComponent} from './add-list/add-list.component'
import {ListService} from './services/list.service'
import {RecordFormCreatorService} from "./sidebar/record-form-creator/record-form-creator.service";
import {LinksValidatorService} from "./sidebar/sidebar-links/links-validator.service";
import {ResizingTextareaComponent} from './common/resizing-textarea/resizing-textarea.component';
import {AddButtonComponent} from './common/add-button/add-button.component';
import {CopyButtonComponent} from './common/copy-button/copy-button.component';
import {CustomListComponent} from "./list/custom-list/custom-list.component";
import {LinksListComponent} from "./list/links-list/links-list.component";
import {ToastComponent} from './toast/toast.component';
import {SystemListComponent} from "./list/system-list/system-list.component";
import {SidebarSensorComponent} from "./sidebar/sidebar-sensor/sidebar-sensor.component";
import {FilterSystemComponent} from "./filter-bar/filter-system/filter-system.component";
import {FilterSystemOptionComponent} from "./filter-bar/filter-system/filter-system-option/filter-system-option.component";
import {LoggedoutInterceptor} from "./interceptors/loggedout.interceptor";
import {FileUploadComponent} from './common/file-upload/file-upload.component';

export const MOMENT_DATETIME_FORMATS = {
    parseInput: 'DD MMM YYYY HH:mm',
    fullPickerInput: 'DD MMM YYYY HH:mm',
    datePickerInput: 'DD MMM YYYY',
    timePickerInput: 'HH:mm',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY'
}

@NgModule({
    declarations: [
        AppComponent,
        RecordListViewComponent,
        MapComponent,
        NavigationComponent,
        ContentComponent,
        SidebarComponent,
        SidebarLinksComponent,
        SideNavigationComponent,
        SidebarAboutComponent,
        SidebarCollectionComponent,
        SidebarTargetComponent,
        SidebarActivityComponent,
        SidebarDetailsComponent,
        SidebarHistoryComponent,
        FilterBarComponent,
        ConfirmModalComponent,
        SidebarMapListComponent,
        FilterComponent,
        ConfirmModalComponent,
        SidebarMapListComponent,
        DatePickerComponent,
        SidebarExistingLinkComponent,
        FilterSearchComponent,
        TargetPopoverComponent,
        BaseRecordItemViewComponent,
        ListRecordItemViewComponent,
        MapRecordItemViewComponent,
        AddListComponent,
        ResizingTextareaComponent,
        AddButtonComponent,
        CopyButtonComponent,
        CustomListComponent,
        LinksListComponent,
        SystemListComponent,
        ToastComponent,
        SidebarSensorComponent,
        FilterSystemComponent,
        FilterSystemOptionComponent,
        FileUploadComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        ClickOutsideModule,
        HttpClientModule,
        FormsModule,
        AgmCoreModule.forRoot(),
        AgmSnazzyInfoWindowModule,
        ReactiveFormsModule,
        OwlDateTimeModule,
        OwlMomentDateTimeModule,
        StoreModule.forRoot({
            rfis: rfiListReducer,
            nav: navigationReducer,
            receivedConfig: mapConfigReducer,
            prismNominations: prismNominationsReducer,
            activeRecord: activeRecordReducer,
            recordSelectOptions: recordSelectOptionsReducer,
            activeRfiSubForm: activeRfiSubFormReducer,
            activeRfiMainForm: activeRfiMainFormReducer,
            currentUser: currentUserReducer,
            showConfirmationModal: showConfirmationModalReducer,
            confirmationReply: confirmationReplyReducer,
            filteredRfis: filteredRfisReducer,
            filteredNominations: filteredNominationsReducer,
            receivedOpenLinks: receivedOpenLinksReducer,
            customListCreated: customListCreatedReducer,
            lists: receivedListReducer,
            showToast: showToastReducer,
            showDndRecordDrops: showDndRecordDropsReducer
        })
    ],
    providers: [
        RfiService,
        RecordService,
        ListService,
        PrismService,
        UserService,
        CoordinatesService,
        TitleCasePipe,
        FilterService,
        {provide: LocationStrategy, useClass: HashLocationStrategy},
        {provide: WindowService, useValue: environment.windowService},
        {provide: OWL_DATE_TIME_FORMATS, useValue: MOMENT_DATETIME_FORMATS},
        {provide: LAZY_MAPS_API_CONFIG, useClass: MapConfigService},
        {provide: MapService, useClass: environment.mapService},
        RecordFormCreatorService,
        LinksValidatorService,
        {provide: HTTP_INTERCEPTORS, useClass: LoggedoutInterceptor, multi: true}
    ],
    bootstrap: [AppComponent]
})
export class AppModule {}
