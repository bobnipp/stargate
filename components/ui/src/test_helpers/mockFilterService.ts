import {BehaviorSubject} from "rxjs/internal/BehaviorSubject";
import {getDefaultRecordFilterState, SelectedFilterOptionState} from "../app/models/record-filter-state.model";

export class MockFilterService {
    hasFilters() {}

    clearAllFilters() {}

    updateFilter() {}

    updateSearchFilter() {}

    getFilterState() {}

    applyFilters() {}

    updateSearchFilterDontApply() {}

    storedFilters = new BehaviorSubject<SelectedFilterOptionState>(getDefaultRecordFilterState())
}