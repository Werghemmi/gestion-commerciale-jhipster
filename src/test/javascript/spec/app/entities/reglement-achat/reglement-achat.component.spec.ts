import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { ReglementAchatComponent } from 'app/entities/reglement-achat/reglement-achat.component';
import { ReglementAchatService } from 'app/entities/reglement-achat/reglement-achat.service';
import { ReglementAchat } from 'app/shared/model/reglement-achat.model';

describe('Component Tests', () => {
  describe('ReglementAchat Management Component', () => {
    let comp: ReglementAchatComponent;
    let fixture: ComponentFixture<ReglementAchatComponent>;
    let service: ReglementAchatService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [ReglementAchatComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(ReglementAchatComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReglementAchatComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReglementAchatService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ReglementAchat(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.reglementAchats && comp.reglementAchats[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ReglementAchat(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.reglementAchats && comp.reglementAchats[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
